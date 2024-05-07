$(document).on("click", "#btnagregar", function(){
    $("#exampleModalLabel").html("Nuevo Plano");
        $("#hddplanoid").val("0");
        cargarCboDistrito(0);
        vaciarCbo("#cbodistrito", true);
        $("#switchestado").prop("checked", true);
        $("#modalplano").modal("show");
})

$(document).on("click","#btnguardar", function(){
$.ajax({
        type: "POST",
        url: "/plano/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            planoid: $("#hddplanoid").val(),
            distritoid: $("#cbodistrito").val(),
            estado: $("#switchestado").val()
        }),
        success: function(resultado) {
                    if(resultado.respuesta){
                        listarPlano();
                        $("#msjerror").hide();
                        $("#divmensaje").html("");
                        $("#divmensaje").append(
                            `<div class="alert alert-success text-center" role="alert">${resultado.mensaje}` +
                            `</div>`
                        );
                        $("#modaldepartamento").modal("hide");
                    } else {
                        $("#msjerror").show();
                        $("#msjerror").html("");
                        $("#msjerror").html(resultado.mensaje);
                    }
                }
    })
})


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
    $.ajax({
            type: "PUT",
            url: "/plano/actualizacion",
            contentType: "application/json",
            data: JSON.stringify({
                planoid: $("#hddplanoid").val(),
                distritoid: $("#cbodistrito").val(),
                estado: $("#switchestado").val()
            }),
            success: function(resultado){
                if(resultado.respuesta) {
                    listarPlano();
                    $("#divmensaje").html("");
                    $("#divmensaje").append(
                        `<div class="alert alert-primary text-center" role="alert">${resultado.mensaje}` +
                        `</div>`
                    );
                    $("#modalplano").modal("hide");
                } else {
                    $("#msjerror").show();
                    $("#msjerror").html("");
                    $("#msjerror").html(resultado.mensaje);
                }
            }
        })
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