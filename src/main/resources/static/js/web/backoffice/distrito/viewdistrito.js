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
            if(resultado.respuesta){
                listarDistritos();
                alertaDeRespuesta("",resultado.mensaje,"success");
                $("#modaldistrito").modal("hide");
            }else{
                alertaDeRespuesta("",resultado.mensaje,"error");
            }
        }
    })
})

$(document).on("click", ".btneliminar", function() {
    Swal.fire({
        title: "¿Eliminar Distrito?",
        text: "Esta acción no podrá deshacerse",
        icon: "warning",
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        cancelButtonColor: "#FF0C27",
        confirmButtonText: "Si, eliminar",
        confirmButtonColor: "#00FF09"
    }).then((result) => {
        if(result.isConfirmed) {
            $.ajax({
                type: "DELETE",
                url: "/distrito/eliminar/" + $(this).attr("data-distid"),
                contentType: "application/json",
                success: function (resultado) {
                    if (resultado.respuesta) {
                            listarDistritos();
                            alertaDeRespuesta("", resultado.mensaje, "success");
                    } else {
                        alertaDeRespuesta ("", resultado.mensaje, "error");
                    }
                }
            })
        }
    })
 })

 function listarDistritos(){

    $.ajax({
        type: "GET",
        url: "/distrito/lista",
        dataType: "json",
        success: function (resultado) {
            $("#tbldistrito > tbody").html("");
            $.each(resultado, function(index, value){
            $("#tbldistrito > tbody").append(
                `<tr>` +
                    `<td>${value.distritoid}</td>` +
                    `<td>${value.nomdist}</td>` +
                     `<td>${value.provincia.nomprov}</td>` +
                    `<td>${value.provincia.departamento.nomdepa}</td>` +
                    `<td>` +
                        `<button type="button" class="btn btn-primary btneditar"`+
                                    `data-distid="${value.distritoid}"` +
                                    `data-nomdist="${value.nomdist}"` +
                                    `data-provid="${value.provincia.provinciaid}"` +
                                    `data-depaid="${value.provincia.departamento.departamentoid}">` +
                                    `<i class="bi bi-pencil"></i>` +
                        `</button>` +
                    `</td>` +
                    `<td>` +
                        `<button type="button" class="btn btn-danger btneliminar" ` +
                            `data-distid="${value.distritoid}">` +
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

function alertaDeRespuesta(_title, _text,_icon){
    Swal.fire({
        title: _title,
        text: _text,
        icon: _icon,
        timer: 1500,
        showConfirmButton: false
    })
}