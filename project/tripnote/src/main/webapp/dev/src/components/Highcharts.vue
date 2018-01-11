<template>
	<div>{{date}}</div>

</template>

<script>
import Highcharts from 'highcharts'

export default {
	name: "HighCharts",
	props: {
		chartType: {
			type: String,
			required: true
		},
		series: {
			type: Array,
			required: true
		},
		date: {
			type: Array,
			required: true
		}
	},
	data: function() {
		return {
			target: undefined
		}
	},
	watch: {
		series: function(val) {
			this.draw()
		},
		date: function(val) {
			this.draw()
		}
	},
	mounted: function() {
		this.draw()
	},
	beforeDestroy: function() {
		this.target.destroy();
	},
	methods: {
		draw(){
			this.target = Highcharts.chart(this.$el, {
				chart: {
					type: this.chartType,
					height: 300,
					spacingRight: 20
				},
				title: {
					text: ''
				},
				subtitle: {
					text: ''
				},
				// colors: ['#059EFE'],
				plotOptions: {
					line: {
						lineWidth: 1.5,
						marker: {
							enabled: true,
							radius: 2.5,
							states: {
								hover: {
									enabled: true,
									radius: 5
								}
							}
						},
						shadow: false
					},
					area: {
						fillColor: '#7e9df2',
						lineColor: '#7e9df2',
						marker:{
							fillColor: '#3BABF3',
							lineColor: '#3BABF3',
							lineWidth: 1,
							radius: 6,
							states: {
								hover: {
									lineWidthPlus: 6,
									lineColor: '#3BABF3',
									fillColor: '#fff'
								}
							}
						}
					},
				},
				xAxis: {
					categories: this.date,
					tickmarkPlacement: 'on',
					title:{
						// text:'时间',
						align:'high',
						style: {
							fontSize: '14px'
						}
					}
				},
				yAxis: {
					title: {
						text: '修改次数',
						style: {
							fontSize: '14px'
						}
					},
					endOnTick: false,
					lineWidth:1,
					minTickInterval: 1,
					allowDecimals: false,
					plotLines: [{
						value: 0,
						width: 1,
						color: '#808080'
					}]
				},
				tooltip: {
					valueSuffix: '',
					crosshairs: {
						width: 1,
						color: '#eee',
						dashStyle: 'solid'
					}
				},
				credits: {
					enabled: false
				},
				legend: {
					enabled: false
				},
				series: this.series
			});
		}
	}
}
</script>
