if(window.sessionStorage.getItem("goodsUpd_obj")==null)
    window.location.href="index.html";
var obj = JSON.parse(window.sessionStorage.getItem("goodsUpd_obj"));
$(function(){
    $(".loading_area").fadeIn();
    $("#goods").addClass("dh_dl_open");
    init();
    $(".loading_area").fadeOut(300);
    bind_price("goodsPrice");
    bind_price("goodsCurrentPrice");
});
var goodsId = obj.id;
var uploadImgIndex = 0;
var imgArr = [];
//c初始化加载内容
function init(){
    $(".loading_area").fadeIn();
    $('#goodsName').val(obj.goodsName);
    $('#goodsPrice').val(utils.toDouble(obj.goodsPrice));
    $('#goodsCurrentPrice').val(utils.toDouble(obj.goodsCurrentPrice));
    $('#goodsDescribe').val(obj.goodsDescribe);
    $('.jodit_wysiwyg').html(obj.goodsDetails);
    $('#editor').html(obj.goodsDetails);
    if(obj.goodsVideo!=""){
        $('.uploadImg').hide();
        document.getElementById('gVideo').setAttribute('src', imgPath+obj.goodsVideo);
        $('#gVideo').show();
    }
    var fileTag = document.getElementById('goodsVideo');
    fileTag.onchange = function () {
        var file = fileTag.files[0];
        if(file!=null){
            var fileReader = new FileReader();
            fileReader.onloadend = function () {
                if (fileReader.readyState == fileReader.DONE) {
                    $('.uploadImg').hide();
                    document.getElementById('gVideo').setAttribute('src', fileReader.result);
                    $('#gVideo').show();
                }
            };
            fileReader.readAsDataURL(file);
        }else{
            $('.uploadImg').show();
            $('#gVideo').hide();
        }
    };
    $('#gVideo').click(function(){
        $('#goodsVideo').click();
    });
    var goodsImgList = new Array();
    goodsImgList = obj.goodsImg.split(',');
    for(var i=0;i<goodsImgList.length;i++){
        var file = goodsImgList[i];
        //以下就是将所有上传的图片回显到页面上，如果需要用canvas进行剪裁再回显以下代码就放入到canvas中
        var li = document.createElement('li');
        li.id = "upload_"+uploadImgIndex;
        document.getElementById("uploadBtn").style.display = "";
        uploadImgIndex++;
        li.className = "upload-li";
        li.innerHTML = '<div class="item image">'+
            '<img class="upload-image" src="'+imgPath+file+'"/>'+
            '<img class="delete-image" src="images/image-delete.png"/>'+
            '</div>';
        document.getElementById("uploadUL").insertBefore(li, document.getElementById("uploadBtn"));
        var uploadArr = document.getElementById("uploadUL").querySelectorAll("li");
        var len = uploadArr.length ;
        if(len > 6){
            document.getElementById("uploadBtn").style.display = "none";
        }
        $(".delete-image").off('click');
        $(".delete-image").on('click',function(){
            var li = $(this).parent().parent()[0];
            var index = $(".upload-ul .upload-li").index(li);
            var liId = li.id;
            $("#"+liId).remove();
            imgArr.splice(index,1);
            document.getElementById("uploadBtn").style.display = "";
        });
    }
    $('#submit_btn').click(function () {
        fileUpload();
    });
}

function selectImage(imgFile){
    var allFile = imgFile.files;
    var totalLen = allFile.length;
    if(yValidate.checkNotEmpty(imgArr) && imgArr.length>0){
        totalLen = totalLen + imgArr.length;
    }
    if(totalLen>6){
        $.jBox.tip("只能上传6张图片");
        return;
    }
    for(var i=0;i<allFile.length;i++){
        var file = allFile[i];
        if(yValidate.checkNotEmpty(imgArr) && imgArr.length>0){
            if(imgArr.length <6){
                imgArr.push(file);
            }
        }else{
            imgArr.push(file);
        }
        //添加一层过滤
        var rFilter = /^(image\/bmp|image\/gif|image\/jpeg|image\/png|image\/tiff)$/i;
        if(!rFilter.test(file.type)) {
            $.jBox.tip("文件格式必须为图片");
            return;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file); //用文件加载器加载文件
        //文件加载完成
        reader.onload = function(e) {
            //计算最后一个窗口right边距，当时处于第6个的时候，right=0
            if((allFile.length + 1)%6 == 0){
                document.getElementById("uploadBtn").style.marginRight = "0px";
            }
            //以下就是将所有上传的图片回显到页面上，如果需要用canvas进行剪裁再回显以下代码就放入到canvas中
            var li = document.createElement('li');
            li.id = "upload_"+uploadImgIndex;
            document.getElementById("uploadBtn").style.display = "";
            uploadImgIndex++;
            li.className = "upload-li";
            li.innerHTML = '<div class="item image">'+
                '<img class="upload-image" src="'+e.target.result+'"/>'+
                '<img class="delete-image" src="images/image-delete.png"/>'+
                '</div>';
            document.getElementById("uploadUL").insertBefore(li, document.getElementById("uploadBtn"));
            var uploadArr = document.getElementById("uploadUL").querySelectorAll("li");
            var len = uploadArr.length ;
            if(len > 6){
                document.getElementById("uploadBtn").style.display = "none";
            }
        };
        reader.onloadend = function(e) {
            $(".delete-image").off('click');
            $(".delete-image").on('click',function(){
                var li = $(this).parent().parent()[0];
                var index = $(".upload-ul .upload-li").index(li);
                var liId = li.id;
                $("#"+liId).remove();
                imgArr.splice(index,1);
                document.getElementById("uploadBtn").style.display = "";
            });
        }
    }
}
function fileUpload() {
    var param = new FormData();
    for (var i = 0; i < imgArr.length; i++) {
        param.append('files', imgArr[i], i);
    }
    param.append("goodsId",goodsId);
    param.append("goodsName", $("#goodsName").val());
    param.append("goodsDescribe", $("#goodsDescribe").val());
    param.append("goodsPrice", $("#goodsPrice").val());
    param.append("goodsCurrentPrice", $("#goodsCurrentPrice").val());
    param.append("goodsDetails", $("#editor").html());
    var goodsVideo = document.getElementById('goodsVideo');
    var fileVideo = goodsVideo.files[0];
    param.append("video",fileVideo);
    param.append("cid", "1");
    if (check()) {
        $.ajax({
            url: serPath + "cmsGoods/upd",
            type: 'POST',
            data: param,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function(data){
                if (data.recode == 0){
                    setTimeout('window.location.href="sel_goods.html"', 1000);
                } else {
                    $.jBox.tip(data.msg);
                }
        },
        error:function(){
            $("body").mLoading("hide");
            $.jBox.tip(res.description || res.message || "上传失败");
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
