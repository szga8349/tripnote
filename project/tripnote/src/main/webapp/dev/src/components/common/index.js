// import TableHeader from "./TableHeader/index"
import Loading from "./Loading/index"
import Select from "./Select/index"
// import NoData from "./NoData/index"
// import MoreLoading from './MoreLoading/index'

const component = [
	// TableHeader,
	Loading,
    Select,
	// NoData,
  // MoreLoading
]
const components = {
	install(Vue) {
		component.map((val) => val.install(Vue))
	}
}

export default components
