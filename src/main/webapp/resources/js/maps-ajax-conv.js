$(document).ready(function() {

    let selectTransportBtn;
    let mapsAmount = 1;
    let points = [];
    let endPointOptionsHtml = "";

    $.getJSON('/points/list', function(response) {
        points = response;
        points.forEach(function(point) {
            endPointOptionsHtml += `<option value="${point.id}">${point.name}</option>`
        })
    });

    $('#maps-table').on('click', '.select-transport-btn',function(e) {
        selectTransportBtn = $(this);
    });

    $("#transports-table").on('click', '#selected-transport-btn' ,(function(e) {

        e.preventDefault();
        let transport = {};

        $(this).parent().siblings('.text').each(function() {
            transport[$(this).attr('name')] = $(this).text();
        });

        selectTransportBtn.parent().before(`<td class=\"transportId\" hidden>${transport.id}</td>` +
            `                <td class=\"transportName\">${transport.name}</td>\n` +
            `                <td class=\"transportSpeed\">${transport.speed}</td>\n` +
            `                <td class=\"transportMaxWeight\">${transport.maxWeight}</td>`);

        selectTransportBtn.parent().remove();

        $("#exampleModal").modal('hide');

    }));

    $('#add-point-btn').click(function(e) {
        e.preventDefault();
        mapsAmount += 1;

        let previousPointDOMElement = $('#maps-table tr:last-child #endPoint option:selected');

        $('#maps-table').append('<tr>\n' +
            `                <td class="serialNumber">${mapsAmount}</td>\n` +
            '                <td class="startPoint">\n' +
            '                    <select disabled id="startPoint" required>\n' +
            `                        <option selected value="${previousPointDOMElement.val()}">${previousPointDOMElement.text()}</option>\n` +
            '                    </select>\n' +
            '                </td>\n' +
            '                <td class="endPoint">\n' +
            '                    <select id="endPoint" required>\n' +
            `${endPointOptionsHtml}` +
            '                    </select>\n' +
            '                </td>\n' +
            '\n' +
            '                <td class="distance">\n' +
            '                    <input type="number" id="distance">\n' +
            '                </td>\n' +
            '\n' +
            '                <td class="transport">\n' +
            '                    <button type="button" class="btn btn-primary select-transport-btn" data-toggle="modal" data-target="#exampleModal">\n' +
            '                        Выбрать транспорт\n' +
            '                    </button>\n' +
            '                </td>\n' +
            '            </tr>')
    });

    $('#submit-btn').click(function(e) {
        e.preventDefault();
        let maps = [];
        let map = {};
        let tdElements;

        $('#maps-table tr').each(function () {
            if ($(this).attr('class') !== 'table-head') {
                let optioon = $(this).children('.startPoint').children('#startPoint option:selected');
                console.log(optioon);
                console.log(optioon.text());
                console.log(optioon.val());
               map = {
                   serialNumber:  $(this).children(".serialNumber").text(),
                   startPointId: $(this).children('.startPoint').children('#startPoint').val(),
                   endPointId: $(this).children('.endPoint').children('#endPoint').val(),
                   distance: $(this).children('.distance').children('#distance').val(),
                   transportId: $(this).children('.transportId').text()
               };
               console.log(map);
               maps.push(map);
            }
        });

        console.log(maps);

        let href = $('#form').attr('action');
        console.log(href);
        $.ajax({
            url: href,
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(maps),
            success: function (data, textStatus, xhr) {
                console.log(data);
                alert($('#form .reload').attr('href'));
                $(location).attr('href', $('#form .reload').attr('href'));
            },
            error: function (xhr, textStatus, errorThrown) {
                let errorBlock =  $('.myForm #errors');
                let response = JSON.parse(xhr.responseText);
                response.errors.forEach(function(error) {
                    errorBlock.append("<p>"+ error +"</p>");
                    /*console.log(error);*/
                });
                errorBlock.css("display", "block");
            }
        })

    });

});