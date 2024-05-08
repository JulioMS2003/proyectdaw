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
                    if(resultado.respuesta){
                        setTimeout(function(){
                            location.reload();
                        }, 1000);
                    }
                }
            })
        }
    })
})

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