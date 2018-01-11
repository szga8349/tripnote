import fetch from '../config/fetch'

export const currentLang = () => fetch('/lang');
