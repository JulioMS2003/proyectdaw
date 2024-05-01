$(document).on("click", "#btnagregar", function(){
    $("#exampleModalLabel").html("Registrar Departamento");
    $("#txtnomdepa").val("");
    $("#hdddepartamentoid").val("0");
    $("#btnguardar").show();
    $("#btnactualizar").hide();
    $("#btneliminar").hide();
    $("#btncerrar").hide();
    $("#msjerror").hide();
    $("#modaldepartamento").modal("show");
})

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/departamento/registro",
        contentType: "application/json",
        data: JSON.stringify({
            departamentoid: $("#hdddepartamentoid").val(),
            nomdepa: $("#txtnomdepa").val()
        }),
        success: function(resultado) {
            if(resultado.respuesta){
                listarDepartamentos();
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

$(document).on("click", "#btnactualizar", function() {
    $.ajax({
        type: "PUT",
        url: "/departamento/actualizacion",
        contentType: "application/json",
        data: JSON.stringify({
            departamentoid: $("#hdddepartamentoid").val(),
            nomdepa: $("#txtnomdepa").val()
        }),
        success: function(resultado){
            if(resultado.respuesta) {
                listarDepartamentos();
                $("#divmensaje").html("");
                $("#divmensaje").append(
                    `<div class="alert alert-primary text-center" role="alert">${resultado.mensaje}` +
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

$(document).on("click", ".btneditar", function(){
    $.ajax({
        type: "GET",
        url: "/departamento/obtener/" + $(this).attr("data-depaid"),
        dataType: "json",
        success: function(resultado) {
            $("#exampleModalLabel").html("Actualizar Departamento");
            $("#hdddepartamentoid").val(resultado.departamentoid);
            $("#txtnomdepa").val(resultado.nomdepa);
            $("#btnguardar").hide();
            $("#btncerrar").hide();
            $("#btneliminar").hide();
            $("#btnactualizar").show();
            $("#msjerror").hide();
        }
    });
    $("#modaldepartamento").modal("show");
})

$(document).on("click", ".btneliminar", function() {
    Swal.fire({
        title: "¿Eliminar Departamento?",
        text: "Esta acción no se podrá deshacer",
        icon: "warning",
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        confirmButtonText: "Sí, eliminar",
        confirmButtonColor: "#00FF09",
        cancelButtonColor: "#FF0C27"
    }).then((result) => {
        if(result.isConfirmed) {
            $.ajax({
                type: "DELETE",
                url: "/departamento/eliminacion/" + $(this).attr("data-depaid"),
                contentType: "application/json",
                success: function(resultado) {
                    if(resultado.respuesta) {
                        listarDepartamentos();
                        Swal.fire({
                            title: resultado.mensaje,
                            icon: "success",
                            timer: 2000,
                            showConfirmButton: false
                        });
                    } else {
                        Swal.fire({
                            title: resultado.mensaje,
                            icon: "error",
                            timer: 2000,
                            showConfirmButton: false
                        })
                    }
                }
            })
        }
    })
})

function listarDepartamentos(){
    $.ajax({
        type: "GET",
        url: "/departamento/lista",
        dataType: "json",
        success: function(resultado){
            $("#tbldepartamento > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tbldepartamento > tbody").append(
                    `<tr>` +
                        `<td class="text-center">${value.departamentoid}</td>` +
                        `<td class="text-center">${value.nomdepa}</td>` +
                        `<td class="text-center">` +
                            `<button style="width: 100px" type="button" class="btn btn-primary btneditar"` +
                                     `data-depaid="${value.departamentoid}">Editar` +
                            `</button>` +
                        `</td>` +
                        `<td class="text-center">` +
                            `<button style="width: 100px" type="button" class="btn btn-danger btneliminar"` +
                                     `data-depaid="${value.departamentoid}">Eliminar` +
                            `</button>` +
                        `</td>` +
                    `</tr>`
                )
            })
        }
    })
}