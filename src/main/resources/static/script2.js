         function setActionUrl() {
                var authorId = $('#author').val();
                var form = $('#articleForm');
                var articleId = form.find('input[name="id"]').val();
                var action = ' /updateArticle/article/' + articleId + '/author/' + authorId;
                form.attr('action', action);
            }
           
            // Call the function initially to set the action if the form is pre-filled
            setActionUrl();

            // Call the function whenever the author selection changes
            $('#author').change(function() {
                setActionUrl();
            });
    
     var currentImageName = $('#bannerdata').val();
     if(currentImageName!=='NA'){
		                     	$('#bannerImage').removeAttr('hidden');

	 }
            // This should be the file name of the current image
            console.log("current image=",currentImageName)
            if (currentImageName) {
                $.ajax({
                    url: 'http://localhost:8080/image/'+currentImageName,
                    method: 'GET',
                    
                    xhrFields: {
                        responseType: 'blob'
                    },
                    success: function(data) {
                        var url = URL.createObjectURL(data);
                              console.log(url)
                        $('#bannerImage').attr('src', url);
                    },
                    error: function() {
						
                        console.error('Failed to fetch image from the database');
                    }
                });
            }
            $('#bannerInput').change(function() {
                var input = this;
                if (input.files && input.files[0]) {
                	$('#bannerImage').removeAttr('hidden');
                    var reader = new FileReader();
                    reader.onload = function(e) {
                        $('#bannerImage').attr('src', e.target.result);
                    };
                    reader.readAsDataURL(input.files[0]);
                }
            });