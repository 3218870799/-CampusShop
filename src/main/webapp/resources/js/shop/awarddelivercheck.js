$(function() {
	var shopId = 1;
	var awardName = '';

	function getList() {
		var listUrl = '/CampusShop/shopadmin/listuserawardmapsbyshop?pageIndex=1&pageSize=9999&shopId='
				+ shopId + '&awardName=' + awardName;
		$.getJSON(listUrl, function(data) {
			if (data.success) {
				var userAwardMapList = data.userAwardMapList;
				var tempHtml = '';
				userAwardMapList.map(function(item, index) {
					tempHtml += '' + '<div class="row row-awarddeliver">'
							+ '<div class="col-20">' + item.award.awardName + '</div>'
							+ '<div class="col-40 awarddeliver-time">' + new Date(item.createTime).Format("yyyy-MM-dd") + '</div>' 
							+ '<div class="col-25">' + item.user.name + '</div>'
							+ '<div class="col-15">' + item.point + '</div>'
							+ '</div>';
				});
				$('.awarddeliver-wrap').html(tempHtml);
			}
		});
	}

	$('#search').on('input', function(e) {
		awardName = e.target.value;
		$('.awarddeliver-wrap').empty();
		getList();
	});

	getList();
});