/**
 *@author :hly
 *@github :https://github.com/huangliangyun
 @blog :blog.csdn.net/Sirius_hly
 *@date :2018/11/1
 */

/*静态页面的JS*/

//需要定义成全局才有效
var now = -1;//左右浮动
var num;//判断左右
function setMSGInHtml() {



    var send_btn = document.getElementById('send_btn');
    var send_txt = document.getElementById('send_txt');
    var chat_ul = document.getElementById('chat_ul');
    var chat_span = chat_ul.getElementsByTagName('span');
    var chat_img = chat_ul.getElementsByTagName('img');
    //JSON字符串转化为对象
    var message = send_txt.value;
    //alert(message.message.from)
    if (num == 1)
    //若果是自己则在右边
        num = 0;
    else
        num = 1;
//http://123.56.219.53/MyCourse/static/img/img_26.jpg
    //../../static/img/img_26.jpg

    if (num == 0) {
        chat_ul.innerHTML += '<li><img src="../img/hly.jpg"><span>' + message + '</span>';
        now++;
        chat_span[now].className = 'spanright';
        chat_img[now].className = 'imgright';
    }
    else {
        chat_ul.innerHTML += '<li><img src="../img/hly2.jpg"><span>' + message + '</span>';
        now++;
        chat_span[now].className = 'spanleft';
        chat_img[now].className = 'imgleft';
    }

    send_txt.value = '';
}

