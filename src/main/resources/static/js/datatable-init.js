console.log('init running');


$(document).ready(function () {
    if ($.fn.DataTable.isDataTable('#dataTable')) {
        $('#dataTable').DataTable().destroy();
    }

    $('#dataTable').DataTable({
        pageLength: 10,
        lengthChange: false,
        searching: true,
        order: [[0, 'desc']],
        columnDefs: [
            { targets: -1, orderable: false }   // Links column
        ]
        // Add buttons only if you want them:
        // buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
        // dom: 'Bfrtip'
    });
});