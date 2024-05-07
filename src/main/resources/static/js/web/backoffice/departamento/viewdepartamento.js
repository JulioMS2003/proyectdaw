$(document).on("click", "#btnagregar", function(){
    $("#exampleModalLabel").html("Registrar Departamento");
    $("#txtnomdepa").val("");
    $("#hdddepartamentoid").val("0");
    $("#btnguardar").show();
    $("#btnactualizar").hide();
    $("#btneliminar").hide();
    $("#btncerrar").hide();
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
            alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success": "error");
            if(resultado.respuesta){
                listarDepartamentos();
                $("#modaldepartamento").modal("hide");
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
            alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success": "error");
            if(resultado.respuesta) {
                listarDepartamentos();
                $("#modaldepartamento").modal("hide");
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
            $("#modaldepartamento").modal("show");
        }
    });
})

$(document).on("click", ".btneliminar", function() {
    Swal.fire({
        title: "¿Eliminar Departamento?",
        text: "Esta acción no se podrá deshacer",
        icon: "warning",
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        confirmButtonText: "Sí, eliminar",
        confirmButtonColor: "#198754",
        cancelButtonColor: "#dc3545"
    }).then((result) => {
        if(result.isConfirmed) {
            $.ajax({
                type: "DELETE",
                url: "/departamento/eliminacion/" + $(this).attr("data-depaid"),
                contentType: "application/json",
                success: function(resultado) {
                    alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success": "error");
                    if(resultado.respuesta) {
                        listarDepartamentos();
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
                        `<td>${value.nomdepa}</td>` +
                        `<td class="text-center">` +
                            `<button type="button" class="btn btn-primary btneditar"` +
                                     `data-depaid="${value.departamentoid}">` +
                                `<i class="bi bi-pencil"></i>` +
                            `</button>` +
                        `</td>` +
                        `<td class="text-center">` +
                            `<button type="button" class="btn btn-danger btneliminar"` +
                                     `data-depaid="${value.departamentoid}">` +
                                `<i class="bi bi-trash"></i>` +
                            `</button>` +
                        `</td>` +
                    `</tr>`
                )
            })
        }
    })
}

function alertaDeRespuesta(_title, _text, _icon) {
    Swal.fire({
        title: _title,
        text: _text,
        icon: _icon,
        timer: 5000,
        showConfirmButton: false
    })
}