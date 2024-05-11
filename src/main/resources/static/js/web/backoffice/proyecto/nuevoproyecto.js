var nroplano = 0;
var idplanos = [];

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

$(document).on("click", "#btnagregarplano", function(){
    if($("#txtplanoid").val() == null || $("#txtplanoid").val() == "" || $("#txtplanoid").val().length != 7) {
        alertaDeRespuesta(" ", "Ingresar código de plano (7 caracteres)", "error");
        return;
    }
    if($.inArray($("#txtplanoid").val(), idplanos) != -1) {
        alertaDeRespuesta(" ", "Plano '" + $("#txtplanoid").val() + "' ya existe en este proyecto", "error");
        return;
    }

    nroplano++;
    $("#divplanos").append(
        `<div id="${'divplanonro' + nroplano}" class="container col-6">` +
            `<div class="row mb-3 bg-white p-2 rounded border border-1 mx-3 my-2">` +
                `<div class="col mt-2">` +
                    `<h5>${$("#txtplanoid").val()}</h5>` +
                `</div>` +
                `<div class="col text-end">` +
                    `<button type="button" class="btn btn-danger btneliminar" ` +
                             `data-planodiv="${'divplanonro' + nroplano}" data-planoid='${$("#txtplanoid").val()}'>` +
                        `<i class="bi bi-trash"></i>` +
                    `</button>` +
                `</div>` +
            `</div>` +
        `</div>`
    );
    idplanos.push($("#txtplanoid").val());
    $("#txtplanoid").val("");
})

$(document).on("click", ".btneliminar", function(){
    $("#" + $(this).attr("data-planodiv")).remove();
    idplanos.splice(idplanos.indexOf($(this).attr("data-planoid")), 1);
})

$(document).on("click", "#btngenerar", function(){
    $.ajax({
        type: "POST",
        url: "/proyecto/nuevo/generar",
        contentType: "application/json",
        data: JSON.stringify({
            empresaid: $("#cboempresa").val(),
            fecinicio: $("#txtfecinicio").val(),
            distritoid: $("#cbodistrito").val(),
            planos: idplanos
        }),
        success: function(resultado) {
            alertaDeRespuesta(" ", resultado.mensaje, resultado.respuesta ? "success": "error");
            if(resultado.respuesta) {
                setTimeout(function() {
                    window.location.href = "/proyecto";
                }, 1000);
            }
        }
    })
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

function alertaDeRespuesta(_title, _text, _icon) {
    Swal.fire({
        title: _title,
        text: _text,
        icon: _icon,
        timer: 5000,
        showConfirmButton: false
    })
}