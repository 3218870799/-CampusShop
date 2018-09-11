$(function() {
    var url = '/CampusShop/frontend/listmainpageinfo';

    $.getJSON(url, function (data) {
        if (data.success) {
            var headLineList = data.headLineList;
            var swiperHtml = '';
            headLineList.map(function (item, index) {
                swiperHtml += ''
                            + '<div class="swiper-slide img-wrap">'
                            +      '<img class="banner-img" src="'+ item.lineImg +'" alt="'+ item.lineName +'">'
                            + '</div>';
            });
            //将轮播图赋值给前端HTML控件
            $('.swiper-wrapper').html(swiperHtml);
            //设定轮播图轮换时间为1秒
            $(".swiper-container").swiper({
                autoplay: 1000,
                autoplayDisableOnInteraction: false
            });
            //获取后台传递过来的大类列表
            var shopCategoryList = data.shopCategoryList;
            var categoryHtml = '';
            //遍历大类列表，拼接出两两一行的类别
            shopCategoryList.map(function (item, index) {
                categoryHtml += ''
                             +  '<div class="col-50 shop-classify" data-category='+ item.shopCategoryId +'>'
                             +      '<div class="word">'
                             +          '<p class="shop-title">'+ item.shopCategoryName +'</p>'
                             +          '<p class="shop-desc">'+ item.shopCategoryDesc +'</p>'
                             +      '</div>'
                             +      '<div class="shop-classify-img-warp">'
                             +          '<img class="shop-img" src="'+ item.shopCategoryImg +'">'
                             +      '</div>'
                             +  '</div>';
            });
            //将拼接完成的赋值给前端页面
            $('.row').html(categoryHtml);
        }
    });

    //点击显示我的
    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });

    $('.row').on('click', '.shop-classify', function (e) {
        var shopCategoryId = e.currentTarget.dataset.category;
        var newUrl = '/CampusShop/frontend/shoplist?parentId=' + shopCategoryId;
        window.location.href = newUrl;
    });

});
