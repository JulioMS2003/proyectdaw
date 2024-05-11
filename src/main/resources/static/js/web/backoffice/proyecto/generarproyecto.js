var asignaciones = []

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

$(document).on("click", ".btnasignacion", function(){
    $.ajax({
        type: "GET",
        url: "/asignacion/buscar/" + $(this).attr("data-proyid"),
        dataType: "json",
        success: function(resultado) {
            $("#divasignacionplanos").html("");
            asignaciones = [];
            $.each(resultado, function(index, value){
                $("#divasignacionplanos").append(
                    `<div class="form-floating mb-3">` +
                        `<input type="text" class="form-control" ` +
                                `id="txtplano${value.plano.planoid}" placeholder="Plano" ` +
                                `value="${value.plano.planoid}" readonly>` +
                        `<label for="txtplano${value.plano.planoid}">Plano</label>` +
                    `</div>` +
                    `<div class="form-floating mb-3">` +
                        `<select class="form-select cboempleados" ` +
                                 `id="cboempleado${value.asignacionid}" ` +
                                 `data-asignacionid="${value.asignacionid}" ` +
                                 `aria-label="Floating label select example"> ` +
                            `<option value="-1">Seleccionar opción</option>` +
                        `</select>` +
                        `<label for="cboempleado${value.plano.planoid}">Empleado</label>` +
                    `</div>` +
                    `<hr />`
                );
                cargarEmpleadosActivos(`#cboempleado${value.asignacionid}`);
                asignaciones.push({
                    asignacionid: value.asignacionid,
                    proyectoid: value.proyecto.proyectoid,
                    planoid: value.plano.planoid,
                    empleadoid: $(`#cboempleado${value.asignacionid}`).val()
                });
            });

            $(".cboempleados").change(function(){
                var asignacionid = $(this).attr("data-asignacionid");
                var empleadoid = $(this).val();
                asignaciones.find(a => a.asignacionid == asignacionid).empleadoid = empleadoid;
            });

            $("#modalasignacion").modal("show");
        }
    })
})

$(document).on("click", "#btnguardarasignacion", function(){
    $.ajax({
        type: "PUT",
        url: "/asignacion/asignar-empleado",
        contentType: "application/json",
        data: JSON.stringify(asignaciones),
        success: function(resultado) {
            alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success" : "error");
            if(resultado.respuesta) {
                setTimeout(function(){
                    location.reload();
                }, 1000);
            }
        }
    })
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

$(document).on("click", ".btnfinalizar", function(){
    Swal.fire({
        title: "¿Finalizar Proyecto?",
        icon: "question",
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        cancelButtonColor: "#FF0C27",
        confirmButtonText: "Si, cancelar",
        confirmButtonColor: "#00FF09"
    }).then((result) => {
        if(result.isConfirmed) {
            $.ajax({
                type: "PUT",
                url: "/proyecto/finalizar/" + $(this).attr("data-proyid"),
                success: function(resultado) {
                    alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success" : "error");
                    if(resultado.respuesta) {
                        setTimeout(function(){
                            location.reload();
                        }, 1000);
                    }
                }
            })
        }
    })
})

function cargarEmpleadosActivos(cbo) {
    $.ajax({
        type: "GET",
        url: "/empleado/lista/activos/disponibles",
        dataType: "json",
        success: function(resultado) {
            $.each(resultado, function(index, value){
                $(cbo).append(
                    `<option value="${value.empleadoid}">${value.apeemp + ' ' + value.nomemp}</option>`
                )
            })
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