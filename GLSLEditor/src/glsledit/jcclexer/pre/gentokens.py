import re
p0 = re.compile("\s*glsl_type\(([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\)")
p1 = re.compile("\s*glsl_type\(([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\)")
p2 = re.compile("\s*\{([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\,\s*([^\,]*)\s*}\,\s*$")
o = open("GLSLTokenId.txt", 'w')
jj = open("GLSLToken.jj", 'w')
jj.write('TOKEN :\n{\n')
i = 1
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
		if (i == 1):
			jj.write('  < ' + label + ': "' + token + '" >\n')
		else:
			jj.write('| < ' + label + ': "' + token + '" >\n')
			o.write('\t\t\t\tnew GLSLTokenId("' + label + '", "' + category + '", JavaParserConstants.' + label + '),\n')
	#o.write(output + "\n")
	
	i = i + 1
	
jj.write('}\n')

o.close()

# import re
# p = re.compile("^\s+int\s(.*)\s=\s([0-9]+);$")
# o = open("GLSLTokenId.txt", 'w')
# for line in open("JavaParserConstants.java", 'r'):
# 	if (p.match(line)):
# 		category = "keyword"
# 		output =  '\t\t\t\tnew GLSLTokenId("' + p.match(line).group(1) + '", "' + category + '", JavaParserConstants.' + p.match(line).group(1) + '),\n'
# 		print output
# 		o.write(output)
# o.close()
