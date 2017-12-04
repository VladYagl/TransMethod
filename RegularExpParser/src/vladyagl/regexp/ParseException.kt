package vladyagl.regexp

class ParseException(msg: String, position: Int? = null) : Exception(msg + if (position != null) {" at $position"} else {""})
