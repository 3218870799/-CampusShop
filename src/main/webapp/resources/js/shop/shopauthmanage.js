 $(function() {
    var shopId = 29;
    var listUrl = '/CampusShop/shopadmin/listshopauthmapsbyshop?pageIndex=1&pageSize=9999&shopId=' + shopId;
    var modifyUrl = '/CampusShop/shopadmin/modifyshopauthmap';
    var deleteUrl = '/CampusShop/shopadmin/removeshopauthmap';

    function getList() {
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var shopauthList = data.shopAuthMapList;
                var tempHtml = '';
                shopauthList.map(function (item, index) {
                	var textOp="恢复";
                	var contraryStatus=0;
                	if(item.enableStatus==1){
                		//若状态值为一，则表明授权生效，操作变为删除
                		textOp = "删除";
                		contraryStatus=0;
                	}else{
                		contraryStatus=1;
                	}
                	tempHtml += ''
                        +      '<div class="row row-shopauth">'
                        +          '<div class="col-40">'+ item.employee.name +'</div>';
                	
                	if(item.titleFlag!=0){
                		//若不是店家本人的授权信息，则加入编辑以及修改状态等操作
                        tempHtml +='<div class="col-20">'+ item.title +'</div>'
                            +          '<div class="col-40">'
                            +              '<a href="#" class="edit" data-employee-id="'+ item.employee.userId +'" data-auth-id="'+ item.shopAuthId +'" data-status="'+ item.enableStatus +'">编辑</a>'
                            +              '<a href="#" class="delete" data-employee-id="'+ item.employee.userId +'" data-auth-id="'+ item.shopAuthId +'" data-status="'+ item.enableStatus +'">'+ textOp +'</a>'
                            +          '</div>'
                	}else{
                		//若为店家，且不允许操作
                		tempHtml+='<div class="col-20">'+item.title+'</div>'
                				 +'<div class="col-40">'+'<span>不可操作</span>'+'</div>'
                	}
                	tempHtml+='</div>';
                });
                $('.shopauth-wrap').html(tempHtml);
            }
        });
    }

    getList();

    function deleteItem(id) {
        $.confirm('确定么?', function () {
            $.ajax({
                url: modifyUrl,
                type: 'POST',
                data: {
                	//将json参数转化为字符串
                	shopAuthMapStr:JSON.stringify(shopAuth),
                	statusChange:true,
                },
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        $.toast('删除成功！');
                        getList();
                    } else {
                        $.toast('删除失败！');
                    }
                }
            });
        });
    }
    function changeStatus(id,status) {
    	var shopAuth={};
    	shopAuth.shopAuthId=id;
    	shopAuth.enableStatus=status;
        $.confirm('确定么?', function () {
            $.ajax({
                url: deleteUrl,
                type: 'POST',
                data: {
                    shopAuthId: id,
                },
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        $.toast('删除成功！');
                        getList();
                    } else {
                        $.toast('删除失败！');
                    }
                }
            });
        });
    }

    $('.shopauth-wrap').on('click', 'a', function (e) {
        var target = $(e.currentTarget);
        if (target.hasClass('edit')) {
            window.location.href = '/CampusShop/shopadmin/shopauthedit?shopauthId=' + e.currentTarget.dataset.authId;
        } else if (target.hasClass('status')) {
        	changeStatus(e.currentTarget.dataset.authId,e.currentTarget.dataset.status);
           // deleteItem(e.currentTarget.dataset.authId);
        }
    });

    $('#new').click(function () {
         window.location.href = '/CampusShop/shopadmin/shopauthedit';
    });
});