$(document).ready(function() {
					
					// 車型選擇------------------------------------------------------
					$('#myCarousel').carousel({
						interval : false
					});

					var clickEvent = false;
					$('#myCarousel')
							.on('click', '.nav a', function() {
								clickEvent = true;
								$('.nav li').removeClass('active');
								$(this).parent().addClass('active');
							})
							.on(
									'slid.bs.carousel',
									function(e) {
										if (!clickEvent) {
											var count = $('.nav').children().length - 1;
											var current = $('.nav li.active');
											current.removeClass('active')
													.next().addClass('active');
											var id = parseInt(current
													.data('slide-to'));
											if (count == id) {
												$('.nav li').first().addClass(
														'active');
											}
										}
										clickEvent = false;
									});

				
					// 車型選擇------------------------------------------------------

					
					
					
					
					// Initialize tooltips
					$('.nav-tabs > li a[title]').tooltip();

					// Wizard
					$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {

						var $target = $(e.target);

						if ($target.parent().hasClass('disabled')) {
							return false;
						}
					});

					$(".next-step").click(function(e) {

						var $active = $('.wizard .nav-tabs li.active');
						$active.next().removeClass('disabled');
						nextTab($active);

					});
					$(".prev-step").click(function(e) {

						var $active = $('.wizard .nav-tabs li.active');
						prevTab($active);

					});
					
					function nextTab(elem) {
						$(elem).next().find('a[data-toggle="tab"]').click();
					}
					function prevTab(elem) {
						$(elem).prev().find('a[data-toggle="tab"]').click();
					}

					// =============================日期選擇======================================
					
					// =============================表單輸出================================
					
					$('.next-step').click(
							function() {
								$("#inputmsg1").html($('li.active>a').text());
								$("#inputmsg2").html($('#chooseDate').val());
								var txt1 =$('.county').val();
								var txt2 =$('.district').val();
								var txt3 =$('#address').val();
								$("#inputmsg4").html( txt1+ txt2+ txt3);
								
								
								
								$("#cartypefor").val($("#inputmsg1").text());
								$("#datefor").val($('#chooseDate').val());
								$("#daypartfor").val($("input[name='optradio']:checked").val());
							
								$("#countyfor").val($('.county').val());
								$("#districtfor").val('aaa');
								$("#addressfor").val($('#address').val());
								
							});
					$("input[name='optradio']").change(function(){
						$("#inputmsg3").html($("input[name='optradio']:checked").val());
					});
					
				});
