<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="mb-0 text-center">
        <h3>Редактор события</h3>
    </div>

    <form method="post" enctype="multipart/form-data">
        <div class="form-group row">
            <div class="col-sm-6">
                <input type="hidden" value="${(event.id)!''}" name="id"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата события:</label>
            <div class="col-sm-6">
                <input type="date" class="form-control" value="${(date)!''}" name="date"
                       placeholder="выберите дату"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Операция:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" value="${(event.typeOfWork)!''}" name="typeOfWork"
                       placeholder=""/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Расходные материалы:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" value="${(event.consumables)!''}" name="consumables"
                       placeholder=""/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Количество литров:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" value="${(event.numberOfLitres?string("##0.00"))!''}" name="numberOfLitres"
                       placeholder=""/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Стоимость:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" value="${(event.price?string("##0.00"))!''}" name="price"
                       placeholder=""/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Показания одометра:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" value="${(event.odometerReading?string("##0.00"))!''}" name="odometerReading"
                       placeholder=""/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Примечание:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" value="${(event.note)!''}" name="note"
                       placeholder=""/>
            </div>
        </div>
        <input type="hidden" value="${_csrf.token}" name="_csrf" />
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Сохранить</button>
        </div>
    </form>

</@c.page>