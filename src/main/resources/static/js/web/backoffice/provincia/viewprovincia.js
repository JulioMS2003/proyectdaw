$(document).on("click", "#btnnuevo", function() {
    $("#exampleModalLabel").html("Nueva Provincia");
    $("#hddprovinciaid").val("0");
    $("#txtnomprov").val("");
    cargarCboDepartamento (0);
    $("#modalprovincia").modal("show");
})

$(document).on("click", ".btneditar", function() {
    $("#exampleModalLabel").html("Editar Provincia");
    $("#hddprovinciaid").val($(this).attr("data-provid"));
    $("#txtnomprov").val($(this).attr("data-nomprov"));
    cargarCboDepartamento($(this).attr("data-depaid"));
    $("#modalprovincia").modal("show");
})

$(document).on("click", ".btneliminar", function() {
    Swal.fire({
        title: "¿Eliminar Provincia?",
        text: "Esta acción no podrá deshacerse",
        icon: "warning",
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        cancelButtonColor: "#dc3545",
        confirmButtonText: "Si, eliminar",
        confirmButtonColor: "#198754"
}).then((result) => {
    if(result.isConfirmed) {
        $.ajax({
            type: "DELETE",
            url: "/provincia/eliminar/" + $(this).attr("data-provid"),
            contentType: "application/json",
            success: function(resultado) {
                if (resultado.respuesta) {
                    listarProvincias();
                    alertaDeRespuesta (resultado.mensaje,"", "success");
                } else {
                    alertaDeRespuesta (resultado.mensaje, "", "error");
                    }
                }
            })
        }
    })
})

$(document).on("click", "#btnguardar", function() {
    $.ajax({
        type: $("#hddprovinciaid").val() == 0 ? "POST": "PUT",
        url: "/provincia/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            provinciaid: $("#hddprovinciaid").val(),
            nomprov: $("#txtnomprov").val(),
            departamentoid: $("#cbodepartamento").val()
        }),
        success: function (resultado) {
            alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success" : "error");
            if (resultado.respuesta) {
                listarProvincias();
                $("#modalprovincia").modal("hide");
            }
        }
    })
})
function listarProvincias(){
    $.ajax({
        type: "GET",
        url: "/provincia/lista",
        data: "json",
        success: function(resultado) {
            $("#tblprovincia > tbody").html("");
            $.each(resultado, function(index, value) {
                $("#tblprovincia > tbody").append(
                    `<tr>` +
                         `<td>${value.nomprov}</td>` +
                         `<td>${value.departamento.nomdepa}</td>` +
                         `<td class="text-center">` +
                            `<button type="button" class="btn btn-primary btneditar"` +
                            `data-provid="${value.provinciaid}" ` +
                            `data-nomprov="${value.nomprov}"` +
                            `data-depaid="${value.departamento.departamentoid}">` +
                            `<i class="bi bi-pencil"></i>` +
                            `</button>` +
                         `</td>` +
                          `<td class="text-center">` +
                            `<button type="button" class="btn btn-danger btneliminar"` +
                            `data-provid="${value.provinciaid}">` +
                            `<i class="bi bi-trash"></i>` +
                            `</button>` +
                           `</td>` +
                    `</tr>`
                )
            })
        }
    })
 }

 function cargarCboDepartamento (departamentoid) {

    $.ajax({
        type: "GET",
        url: "/departamento/lista",
        dataType: "json",
        success: function (resultado) {
            $("#cbodepartamento").empty();
            $("#cbodepartamento").append(
                `<option value="-1">Seleccionar opción</option>`
            );
            $.each(resultado, function(index, value){
                $("#cbodepartamento").append(
                    `<option value="${value.departamentoid}">${value.nomdepa}</option>`
                )

            });

            if (departamentoid > 0)
                $("#cbodepartamento").val(departamentoid);

        }

    });
 }