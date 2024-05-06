$(document).on("click", "#btnnuevo", function(){
    $("#empresaModalLabel").html("Nueva Empresa");
    $("#hddempresaid").val("0");
    $("#txtnomempresa").val("");
    $("#txtruc").val("");
    $("#switchactivo").prop("checked", true);
    $("#divestado").hide();
    $("#modalempresa").modal("show");
})

$(document).on("click", ".btneditar", function(){
    $("#empresaModalLabel").html("Editar Empresa");
    $("#hddempresaid").val($(this).attr("data-empid"));
    $("#txtnomempresa").val($(this).attr("data-empnom"));
    $("#txtruc").val($(this).attr("data-empruc"));
    $("#switchactivo").prop("checked", $(this).attr("data-empact") ? true : false);
    $("#divestado").show();
    $("#modalempresa").modal("show");
})