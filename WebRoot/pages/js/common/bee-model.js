var BeeModel = function () {
	var $modal = $('#user-modal');
    var initModals = function () {
    	$modal=$('#user-modal');
    }

    return {
        init: function (ids) {
            initModals(ids);
        },
        openModel:function(url){
  		 // $('body').modalmanager('loading');
  		  setTimeout(function(){
  			$modal.modal({remote: url,backdrop: 'static', keyboard: false});
  		  }, 500);
  		  
  		$(function () { 
			$('#user-modal').on('shown.bs.modal', function () {
		      Page.validate();
			})
		});
  		  
  		$(function () { 
			$('#user-modal').on('hidden.bs.modal', function () {
				$(this).removeData("bs.modal");
			})
		});
        },
        closeModel:function(){
        	 $modal.modal('hide');
        }

    };

}();