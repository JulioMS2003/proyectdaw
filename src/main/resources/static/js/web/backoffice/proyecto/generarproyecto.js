$(document).on("click", ".btndetalles", function(){
    $.ajax({
        type: "GET",
        url: "/proyecto/buscar/" + $(this).attr("data-proyid"),
        dataType: "json",
        success: function(resultado) {
            $("#txtempresa").val(resultado.empresa.nomempresa);
            $("#txtfecinicio").val(moment(resultado.fecinicio).format('DD/MM/YYYY'));
            $("#txtfecfin").val(resultado.fecfin == null ? 'No registra' : moment(resultado.fecfin).format('DD/MM/YYYY'));
            ocultarMostrarAlertas(resultado.estado);
        }
    });
    $.ajax({
        type: "GET",
        url: "/asignacion/buscar/" + $(this).attr("data-proyid"),
        dataType: "json",
        success: function(resultado) {
            $("#tblplanos > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblplanos > tbody").append(
                    `<tr>` +
                        `<td>${value.plano.planoid}</td>` +
                        `<td>${value.empleado == null ? 'Sin Asignación' : value.empleado.nomemp + ' ' + value.empleado.apeemp}</td>` +
                    `</tr>`
                )
            });
        }
    })

    $("#modalproyecto").modal("show");
})

$(document).on("click", ".btncancelar", function(){
    Swal.fire({
        title: "¿Cancelar Proyecto?",
        text: "Esta acción no podrá deshacerse",
        icon: "warning",
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        cancelButtonColor: "#FF0C27",
        confirmButtonText: "Si, cancelar",
        confirmButtonColor: "#00FF09"
    }).then((result) => {
        if(result.isConfirmed) {
            $.ajax({
                type: "PUT",
                url: "/proyecto/cancelar/" + $(this).attr("data-proyid"),
                success: function(resultado) {
                    alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success": "error");
                    if(resultado.respuesta)
                        listarProyectos();
                }
            })
        }
    })
})

function listarProyectos(){
    $.ajax({
        type: "GET",
        url: "/proyecto/lista",
        dataType: "json",
        success: function(resultado) {
            $("#tblproyecto > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblproyecto > tbody").append(
                    `<tr>` +
                        `<td>${value.proyectoid}</td>` +
                        `<td>${value.empresa.nomempresa}</td>` +
                        `<td>${moment(value.fecinicio).format('YYYY-MM-DD')}</td>` +
                        `<td>${value.estado == 'E' ? 'En Proceso' : value.estado == 'C' ? 'Cancelado' : 'Finalizado'}</td>` +
                        `<td class="text-center">` +
                            `<button type="button" class="btn btn-success btndetalles" ` +
                                     `data-proyid="${value.proyectoid}">` +
                                `<i class="bi bi-file-earmark-richtext"></i>` +
                            `</button>` +
                        `</td>` +
                        `<td class="text-center">` +
                            `<button type="button" ` +
                                     `class="${value.estado == 'C' || value.estado == 'F' ? 'btn btn-secondary' : 'btn btn-danger btncancelar'}" ` +
                                     `data-proyid="${value.proyectoid}" ` +
                                     `${value.estado == 'C' || value.estado == 'F' ? 'disabled' : ''}>` +
                                `<i class="bi bi-x-circle"></i>` +
                            `</button>` +
                        `</td>` +
                    `</tr>`
                )
            });
        }
    })
}

function ocultarMostrarAlertas(estado) {
    if(estado == 'E') {
        $("#divenproceso").show();
        $("#divfinalizado").hide();
        $("#divcancelado").hide();
    } else if(estado == 'F') {
        $("#divenproceso").hide();
        $("#divfinalizado").show();
        $("#divcancelado").hide();
    } else {
        $("#divenproceso").hide();
        $("#divfinalizado").hide();
        $("#divcancelado").show();
    }
}

function alertaDeRespuesta(_title, _text,_icon){
    Swal.fire({
        title: _title,
        text: _text,
        icon: _icon,
        timer: 5000,
        showConfirmButton: false
    })
}