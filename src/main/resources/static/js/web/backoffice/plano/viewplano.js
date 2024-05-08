$(document).on("click", "#btnagregar", function(){
    $("#exampleModalLabel").html("Nuevo Plano");
        $("#hddplanoid").val("0");
        $("txtplanoid").val();
        cargarCboDistrito(0);
        vaciarCbo("#cbodistrito", true);
        $("#switchestado").prop("checked", true);
        $("#btnactualizar").hide();
        $("#modalplano").modal("show");
})

$(document).on("click","#btnguardar", function(){
$.ajax({
        type: $("#hddplanoid").val() == 0 ? "POST": "PUT",
        url: "/plano/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            planoid: $("#txtplanoid").val(),
            distritoid : $("#cbodistrito").val(),
            estadoid : $("#switchactivo").prop("checked")
        }),
        success: function(resultado) {
            alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success": "error");
            if(resultado.respuesta) {
                listarPlano();
                $("#modalplano").modal("hide");
            }
        }
    })
})

function listarPlano(){
    $.ajax({
        type: "GET",
        url: "/plano/lista",
        dataType: "json",
        success: function(resultado) {
            $("#tblplano > tbody").html("");
            $.each(resultado, function(index, value) {
                $("#tblplano > tbody").append(
                    `<tr>` +
                        `<td>${value.planoid}</td>` +
                        `<td>${value.distritoid}</td>` +
                        `<td>${value.estadoid ? 'true' : 'false' }</td>` +
                        `<td class="text-center">` +
                            `<button type="button" class="btn btn-primary btneditar" ` +
                                     `data-planoid="${value.planoid}"
                                     data-distritoid="${value.distritoid}" data-estadoid="${value.estado}" `                                `<i class="bi bi-pencil"></i>` +
                            `</button>` +
                        `</td>` +
                    `</tr>`
                )
            })
        }
    })
}

$(document).on("click", ".btneliminar", function() {
            $.ajax({
                type: "DELETE",
                url: "/plano/eliminar/" + $(this).attr("data-planoid"),
                contentType: "application/json",
                success: function (resultado) {
                    if (resultado.respuesta) {
                            listarPlano();
                            alertaDeRespuesta("", resultado.mensaje, "success");
                    } else {
                        alertaDeRespuesta ("", resultado.mensaje, "error");
                    }
                }
            })
            })

function cargarCboDistrito (distritoid) {
    $.ajax({
        type: "GET",
        url: "/distrito/lista",
        dataType: "json",
        success: function (resultado) {
            vaciarCbo("#cbodistrito", false);
            $.each(resultado, function(index, value) {
            $("#cbodistrito").append(
            `<option value="${value.distritoid}">${value.nomdist}</option>`
            )
            });
            if (distritoid > 0)
            $("#cbodistrito").val(distritoid);
             else
                $("#cbodistrito").val("-1");
        }
    })
}

$(document).on("click", ".btnactualizar", function(){
                $("#exampleModalLabel").html("Actualizar Plano");
                $("#hddplanoid").val($(this).attr("data-planoid"));
                cargarCboDistrito($(this).attr("data-planoid"));
                $("#switchestado").prop("checked", $(this).attr("data-estadoid") ? true : false);
                $("#btnactualizar").hide();
                $("#btnguardar").show();
                $("#modalplano").modal("show");
    })

function vaciarCbo(cbo,disabled){
    $(cbo).empty();
    $(cbo).append(
        `<option value="-1">Seleccionar opción</option>`
    );
    $(cbo).prop("disabled", disabled);
}


function alertaDeRespuesta(_title, _text,_icon){
    Swal.fire({
        title: _title,
        text: _text,
        icon: _icon,
        timer: 1500,
        showConfirmButton: false
    })
}