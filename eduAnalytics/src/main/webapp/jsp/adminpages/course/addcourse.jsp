<style>
    .browserStat.big {
        display: inline-block;
        width: 49%;
        text-align: center;
        margin-bottom: 20px;
        padding: 0;
    }

    .browserStat {
        display: inline-block;
        width: 32%;
        text-align: center;
        margin: 0;
        padding: 0;
    }

    .browserStat span {
        display: block;
        text-align: center;
        margin-top: 10px;
    }

</style>
<div ng-controller="addcoursecontroller">
    <aside class="right-side">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Upload Courses
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <span ng-bind="retMsg" /><br />
                <b>&nbsp;CSV File</b><span id="noofrecstoimported"></span>
                <table style="width: 30%">
                    <tr>
                        <td>
                            <input type="file" id="csvfile" name="csvfile" accept=".csv" onchange="angular.element(this).scope().attachFile(this)" file-reader="fileContent" /> 
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary" ng-click="uploadDiscipline()">Import CSV</button>
                        </td>
                    </tr>
                </table>
                <br />
                <div id="dvCSV">
                </div>
            </div>
        </section>
    </aside>
</div>