$(function(){
    $(".loading_area").fadeIn();
    $("#article").addClass("dh_dl_open");
    init();
    $(".loading_area").fadeOut(300);
});
var obj, pictures;
function init(){
    obj = JSON.parse(sessionStorage.getItem("article_info_obj"));
    fileUrl = sessionStorage.getItem("fileUrl");
    console.log(obj);
    $('#typeId').html(obj.typeId);
    $('#logo').html('<img style="width: 80px; height: 80px;" src="' + fileUrl + obj.articleLogo + '">');
    $('#title').html(obj.articleTitle);
    pictures = obj.articlePicture.split(",");
    var picture_img="";
    for (var i = 0; i< pictures.length; i++) {
        picture_img+='<img style="width: 80px; height: 80px;" src="' + fileUrl + pictures[i] + '">-'
    }
    $('#picture').html(picture_img.substring(0, picture_img.length-1));
    $('#article_content').html(obj.articleContent);
    $('#userId').html(obj.articleCreateUserId);
    $('#createTime').html(obj.articleCheckTime);
    $('#tjs').html("阅读数：" + obj.articleTotalView + "  <font color=\"#ffc0cb\">点赞数：" + obj.articleTotalLike + "</font>  <font color=\"#00ced1\">分享数：" + obj.articleTotalShare + "</font>");
    $('#online').html(obj.articleOnline == 1 ? "<font color='green'>已上架</font>" : "<font color='red'>未上架</font>");
    $('#check').html(obj.articleCheckYn == 0 ? "<font color='orange'>审核中</font>" : obj.articleCheckYn == 1 ? "<font color='green'>通过</font>" : "<font color='red'>驳回</font>");

    $('.top_rt_btn').click(function(){
        window.history.back();
    });
}