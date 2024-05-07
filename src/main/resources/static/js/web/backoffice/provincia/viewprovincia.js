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
                setTimeout(function(){
                    location.reload();
                }, 1000)
                $("#modalprovincia").modal("hide");
            }
        }
    })
})

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