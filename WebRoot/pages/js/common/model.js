var BeeModel = function () {
	var $modal = $('#user-modal');
    var initModals = function () {
       	$.fn.modalmanager.defaults.resize = true;
	//	$.fn.modalmanager.defaults.spinner = '<div class="loading-spinner fade" style="width: 200px; margin-left: -100px;"><img src="assets/img/ajax-modal-loading.gif" align="middle">&nbsp;<span style="font-weight:300; color: #eee; font-size: 18px; font-family:Open Sans;">&nbsp;Loading...</div>';
       	var $modal = $('#user-modal');
		$('#modal_ajax_demo_btn').on('click', function(){
		  $('body').modalmanager('loading');
		  setTimeout(function(){
		     $modal.load('/system/user/to', '', function(){
		      $modal.modal();
		    });
		  }, 1000);
		});
		
		$(function () { 
			$('#user-modal').on('hide.bs.modal', function () {
		      alert('嘿，我听说您喜欢模态框...');})
		});
		$(function () { 
			$('#user-modal').on('show.bs.modal', function () {
		      alert('开始显示');})
		});
		
		$(function () { 
			$('#user-modal').on('shown.bs.modal', function () {
		      alert('显示完成');})
		});
    }

    return {
        init: function (ids) {
            initModals(ids);
        },
        openModel:function(){
  		  $('body').modalmanager('loading');
  		  setTimeout(function(){
  		     $modal.load('/system/user/to', '', function(){
  		      $modal.modal();
  		    });
  		  }, 1000);
  		
        }

    };

}();