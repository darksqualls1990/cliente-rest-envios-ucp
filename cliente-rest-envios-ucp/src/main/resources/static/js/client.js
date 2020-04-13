jQuery(document).ready(function () {
    // Form Principal

    var $form = $('#clienteFilter');
    $form.on('submit', function (e) {
        e.preventDefault();
        // boton pulsado
        var action = $(document.activeElement).val();
        if(action == 'find'){

            $.ajax({
                url: $form.attr('action'), type: 'post', data: $form.serialize(),
                success: function (data) {
                    $("#formClient").html(data);
                    $('#formClient #birthdate1').datepicker({});
                },
                error: function (XMLHttpRequest) {
                    swal("Mensaje del sistema!", XMLHttpRequest.responseJSON.result, "error");
                }
            });
        }else{

            $.ajax({
                url: '/cliente/new', type: 'get',
                success: function (data) {
                    $("#editModalHolder").html(data);
                    $('#editModal').modal({backdrop: 'static'});
                }
            });
        }
    });

    var $formRegistro = $('#formClient');
    $formRegistro.on('submit', function (e) {
        e.preventDefault();
        // boton pulsado
        var action = $(document.activeElement).val();

        $.ajax({
            url: $formRegistro.attr('action'), type: 'put', data: $formRegistro.serialize(),
            success: function (data) {
                swal("Mensaje del sistema!", data.result, "success");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                swal("Mensaje del sistema!", XMLHttpRequest.responseJSON.result, "error");
            }
        });
    });



    modalEventsActions();
});

function modalEventsActions() {

    // Evento que se ejecuta cuando se abre la ventana
    $(document).on('show.bs.modal', '#editModal', function (e) {

        if($(e.target).attr("id") === "editModal"){
            commonModalActions();
        }

    });

    $(document).on('hide.bs.modal', '#editModal', function (e) {

    });
}

function commonModalActions() {
// Form Modal
    var $formModal = $('#userModalForm');
    $formModal.on('submit', function (e) {
        e.preventDefault();
        // boton pulsado
        //Creo que es mejor establecerle propiedades al formulario
        //asi tiene mas junto todo-

        var action = $(document.activeElement).val();
        $.ajax({
            url: $formModal.attr('action'),type:'post', data: $formModal.serialize(),
            success: function (response) {
                $('#editModal').toggleClass('programmatic');
                $('#editModal').modal('hide');

                swal("Mensaje del sistema!", "Cliente creado correctamente", "success");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                swal("Mensaje del sistema!", XMLHttpRequest.responseJSON.result, "error");
            }
        });
    });

    $("#estadoSel").on("select", function (evt) {
        $('#estadoSel').val(evt.params.data.id);
    });

    $("#idLugarResidencia").on("select", function (evt) {
        $('#idLugarResidencia').val(evt.params.data.id);
    });

    $('#birthdate').datepicker({});

}

function filtro() {

}