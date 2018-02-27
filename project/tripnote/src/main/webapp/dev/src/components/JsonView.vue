<template>
	<div>

	</div>
</template>

<script>
var JSONView = require('jsonview');
export default {
	name: "JsonView",
	props: {
		jsondata: {
			type: Object,
			required: true
		}
	},
	data: function() {
		return {
			target: undefined
		}
	},
	watch: {
		jsondata: function() {
			$(this.$el).JSONView(this.jsondata)
		}
	},
	mounted: function() {
		$(this.$el).JSONView(this.jsondata)
	},
	beforeDestroy: function() {
		// this.target.destroy();
	}
}
</script>

<style>
.schema .con{
border:1px solid #f2f3f5;
}
.jsonview {
font-family: monospace;
font-size: 1.1em;
white-space: pre-wrap;
}
.jsonview .prop {
font-weight: normal;
color: #333
}
.jsonview .null {
color: red;
}
.jsonview .bool {
color: blue;
}
.jsonview .num {
color: blue;
}
.jsonview .string {
color: green;
white-space: pre-wrap;
word-break: break-all;
}
.jsonview .string.multiline {
display: inline-block;
vertical-align: text-top; }
.jsonview .collapser {
position: absolute;
left: -1em;
cursor: pointer; }
.jsonview .collapsible {
trafficion: height 1.2s;
trafficion: width 1.2s; }
.jsonview .collapsible.collapsed {
height: .8em;
width: 1em;
display: inline-block;
overflow: hidden;
margin: 0; }
.jsonview .collapsible.collapsed:before {
content: "…";
width: 1em;
margin-left: .2em; }
.jsonview .collapser.collapsed {
transform: rotate(0deg); }
.jsonview .q {
display: inline-block;
width: 0px;
color: transparent; }
.jsonview li {
position: relative; }
.jsonview ul {
list-style: none;
margin: 0 0 0 2em;
padding: 0; }
.jsonview h1 {
font-size: 1.2em; }

</style>
