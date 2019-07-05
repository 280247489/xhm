$(function(){
    $("#advertise").addClass("dh_dl_open");
    init();
});
function init() {
    $(".loading_area").fadeIn();

    $('#cid').val("1");
    var fileTag = document.getElementById('advertiseLogo');
    fileTag.onchange = function () {
        var file = fileTag.files[0];
        if(file!=null){
            var fileReader = new FileReader();
            fileReader.onloadend = function () {
                if (fileReader.readyState == fileReader.DONE) {
                    $('.uploadImg').hide();
                    document.getElementById('logoImg').setAttribute('src', fileReader.result);
                    $('#logoImg').show();
                }
            };
            fileReader.readAsDataURL(file);
        }else{
            $('.uploadImg').show();
            $('#logoImg').hide();
        }
    };
    $('#logoImg').click(function(){
        $('#advertiseLogo').click();
    });
    $("#submit_btn").click(function(){
        add();
    });

    $(".loading_area").fadeOut(300);
}
function add() {
    var param = new FormData();
    param.append('logo',  document.getElementById('advertiseLogo').files[0]);
    param.append("cid", "1");
    param.append("name", $("#advertiseName").val());
    param.append("content", $("#advertiseContent").html());
    if (check()) {
        $.ajax({
            url: serPath + "cmsAdvertise/add",
            type: 'POST',
            data: param,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data){
                if (data.recode==0) {
                    $.jBox.tip("操作成功.");
                    setTimeout('window.location.href="sel_advertise.html"', 1000);
                }else{
                    $.jBox.tip(data.msg);
                }
        },
        error:function () {
            $.jBox.tip("上传失败");
        }
    });
    }
}
//验证
function check(){
    var flag = true;
    if($('#advertiseName').val()==""){
        flag = false; $.jBox.tip("名称不能为空");
    }else if($('#advertiseContent').html()==""){
        flag = false; $.jBox.tip("详情不能为空");
    }
    return flag;
}