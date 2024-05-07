$(document).on("click","#btnagregar", function(){
    $("#exampleModalLabel").html("Nuevo Plano");
    $("#hddplanoid").val("0");
    cargarCboDistrito(0);
    vaciarCbo("#cbodistrito", true);
    $("#switchestado").prop("checked", true);
    $("#modalplano").modal("show");
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
    $("#planoModalLabel").html("Editar Plano");
    cargarModalPlano($(this).attr("data-planoid"), false, false, false, false);
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