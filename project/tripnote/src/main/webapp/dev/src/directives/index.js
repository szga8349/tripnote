import ellipsis from './ellipsis'
const directiveArr = [
	ellipsis
]

function directive(Vue, ...rest) {
	return Vue.directive(...rest)
}

const directivesObj = {
	install(Vue) {
		directiveArr.map((val) => directive(Vue, val.name, val.install))
	}
}
export default directivesObj
