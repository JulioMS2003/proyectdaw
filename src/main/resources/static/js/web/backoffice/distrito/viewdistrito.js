$(document).on("click","#btnnuevo", function(){
    $("#exampleModalLabel").html("Nuevo Distrito");
    $("#hdddistritoid").val("0");
    $("#txtnomdist").val("");
    cargarCboDepartamento(0);
    vaciarCbo("#cboprovincia", true);
    $("#modaldistrito").modal("show");
})
$(document).on("click", ".btneditar", function() {
    $("#exampleModalLabel").html("Editar Distrito");
    $("#hdddistritoid").val($(this).attr("data-distid"));
    $("#txtnomdist").val($(this).attr("data-nomdist"));
    cargarCboDepartamento($(this).attr("data-depaid"));
    cargarCboProvincia($(this).attr("data-depaid"),$(this).attr("data-provid"));
    $ ("#modaldistrito").modal("show");
})

$(document).on("change", "#cbodepartamento", function() {
    if($("#cbodepartamento").val() == -1) {
        vaciarCbo("#cboprovincia", true);
    } else {
        cargarCboProvincia($("#cbodepartamento").val(), 0);
    }
})
$(document).on("click","#btnguardar",function(){
    $.ajax({
        type: $("#hdddistritoid").val() == 0 ? "POST":"PUT",
        url: "/distrito/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            distritoid: $("#hdddistritoid").val(),
            nomdist: $("#txtnomdist").val(),
            provinciaid: $("#cboprovincia").val()
        }),
        success: function(resultado){
            alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success" : "error");
            if(resultado.respuesta){
                setTimeout(function(){
                    location.reload();
                }, 1000);
                $("#modaldistrito").modal("hide");
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
            vaciarCbo("#cbodepartamento", false);
            $.each(resultado, function(index, value) {
            $("#cbodepartamento").append(
            `<option value="${value.departamentoid}">${value.nomdepa}</option>`
            )
            });
            if (departamentoid > 0)
            $("#cbodepartamento").val(departamentoid);
             else
                $("#cbodepartamento").val("-1");
        }
    })
}

 function cargarCboProvincia (departamentoid, provinciaid) {
    $.ajax({
        type: "GET",
        url: "/provincia/lista/" + departamentoid,
        dataType: "json",
        success: function (resultado) {
            vaciarCbo("#cboprovincia", false);
            $.each(resultado, function(index, value) {
                $("#cboprovincia").append(
                    `<option value="${value.provinciaid}">${value.nomprov}</option>`
                )
            });
            if(provinciaid > 0)
                $("#cboprovincia").val(provinciaid);
            else
                $("#cboprovincia").val("-1");
        }
    })
}

function vaciarCbo(cbo,disabled){
    $(cbo).empty();
    $(cbo).append(
        `<option value="-1">Seleccionar opción</option>`
    );
    $(cbo).prop("disabled", disabled);
}