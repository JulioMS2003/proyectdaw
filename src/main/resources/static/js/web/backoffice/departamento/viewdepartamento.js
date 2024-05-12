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
                setTimeout(function(){
                    location.reload();
                }, 1000);
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
                setTimeout(function(){
                    location.reload();
                }, 1000);
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

function alertaDeRespuesta(_title, _text, _icon) {
    Swal.fire({
        title: _title,
        text: _text,
        icon: _icon,
        timer: 5000,
        showConfirmButton: false
    })
}