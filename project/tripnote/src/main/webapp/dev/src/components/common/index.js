// import TableHeader from "./TableHeader/index"
import Loading from "./Loading/index"
// import NoData from "./NoData/index"
// import MoreLoading from './MoreLoading/index'

const component = [
	// TableHeader,
	Loading,
	// NoData,
  // MoreLoading
]
const components = {
	install(Vue) {
		component.map((val) => val.install(Vue))
	}
}

export default components
