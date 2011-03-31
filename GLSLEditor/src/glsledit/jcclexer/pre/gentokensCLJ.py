import re
p0 = re.compile("\s*glsl_type\(([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\)")
p1 = re.compile("\s*glsl_type\(([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\)")
p2 = re.compile("\s*\{([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\s*}\,\s*$")
jj = open("glsltokenTmp.clj", 'w')
i = 1
jj.write('(def glsl-words {\n')
for line in open("GLSLdef.txt", 'r'):
	category = "type"
	label = ""
	token = ""
	output = ""
	if (p0.match(line)):
		token = p0.match(line).groups()[-1].strip(' \t\"')
		category = "type"
		label = token.upper()
		#output = label + " " + token + " " + category
	elif (p1.match(line)):
		token = p1.match(line).groups()[-1].strip(' \t\"')
		category = "type"
		label = token.upper()
		#output = label + " " + token + " " + category
	elif (p2.match(line)):
		token = p2.match(line).groups()[-1].strip(' \t\"')
		category = "builtin_variable"
		label = token.upper()
		output = label + " " + token + " " + category
	if (len(token) > 0):
		jj.write('"' + token + '" "' + category + '",\n')
	
	i = i + 1
	
jj.write('})\n')
jj.close()

