$(document).on("change", "#cbodepartamento", function(){
    if($("#cbodepartamento").val() == -1) {
        vaciarCbo("#cboprovincia");
        vaciarCbo("#cbodistrito");
        $("#cboprovincia").prop("disabled", true);
        $("#cbodistrito").prop("disabled", true);
    } else {
       cargarCboProvincia($("#cbodepartamento").val());
       $("#cboprovincia").prop("disabled", false);
       $("#cbodistrito").prop("disabled", true);
    }
})

$(document).on("change", "#cboprovincia", function(){
    if($("#cboprovincia").val() == -1) {
        vaciarCbo("#cbodistrito");
        $("#cbodistrito").prop("disabled", true);
    } else {
        cargarCboDistrito($("#cboprovincia").val());
        $("#cbodistrito").prop("disabled", false);
    }
})

function cargarCboProvincia(departamentoid){
    $.ajax({
        type: "GET",
        url: "/provincia/lista/" + departamentoid,
        dataType: "json",
        success: function(resultado) {
            vaciarCbo("#cboprovincia");
            $.each(resultado, function(index, value){
                $("#cboprovincia").append(
                    `<option value="${value.provinciaid}">${value.nomprov}</option>`
                )
            });
        }
    })
}

function cargarCboDistrito(provinciaid){
    $.ajax({
        type: "GET",
        url: "/distrito/lista/" + provinciaid,
        dataType: "json",
        success: function(resultado) {
            vaciarCbo("#cbodistrito");
            $.each(resultado, function(index, value){
                $("#cbodistrito").append(
                    `<option value="${value.distritoid}">${value.nomdist}</option>`
                )
            });
        }
    })
}

function vaciarCbo(cbo){
    $(cbo).empty();
    $(cbo).append(
        `<option value="-1">Seleccionar opción</option>`
    )
}