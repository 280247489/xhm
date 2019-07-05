if(window.sessionStorage.getItem("advertiseUpd_obj")==null)
    window.location.href="index.html";
var obj = JSON.parse(window.sessionStorage.getItem("advertiseUpd_obj"));
$(function(){
    $(".loading_area").fadeIn();
    $("#advertise").addClass("dh_dl_open");
    init();
    $(".loading_area").fadeOut(300);
});
var aId = obj.id;
//c初始化加载内容
function init(){
    $(".loading_area").fadeIn();
    $('#advertiseName').val(obj.advertiseName);
    $('.jodit_wysiwyg').html(obj.advertiseContent);
    $('#advertiseContent').html(obj.advertiseContent);

    if(obj.advertiseLogo!=""){
        $('.uploadImg').hide();
        document.getElementById('logoImg').setAttribute('src', imgPath+obj.advertiseLogo);
        $('#logoImg').show();
    }
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
    $('#submit_btn').click(function () {
        update();
    });
}

function update() {
    var param = new FormData();
    param.append("id",aId)
    param.append('logo',  document.getElementById('advertiseLogo').files[0]);
    param.append("cid", "1");
    param.append("name", $("#advertiseName").val());
    param.append("content", $("#advertiseContent").html());
    if (check()) {
        $.ajax({
            url: serPath + "cmsAdvertise/upd",
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
function check(){
    var flag = true;
    if($('#goodsName').val()==""){
        flag = false; $.jBox.tip("产品名称不能为空");
    }else if($('#goodsPrice').val()==""){
        flag = false; $.jBox.tip("产品价格不能为空");
    }else if($('#goodsDescribe').val()==""){
        flag = false; $.jBox.tip("产品描述不能为空");
    }else if($('#goodsCurrentPrice').val()==""){
        flag = false; $.jBox.tip("产品价格不能为空");
    }else if ($('#editor').html()=="") {
        flag = false; $.jBox.tip("产品详情不能为空");
    }
    return flag;
}
