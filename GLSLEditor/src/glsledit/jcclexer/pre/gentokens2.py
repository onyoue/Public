
import re
p = re.compile("^\s+int\s(.*)\s=\s*([0-9]+);")
o = open("GLSLTokenId2.txt", 'w')
for line in open("JavaParserConstants.java", 'r'):
	if (p.match(line)):
		category = "keyword"
		output =  '\t\t\t\tnew GLSLTokenId("' + p.match(line).group(1) + '", "' + category + '", JavaParserConstants.' + p.match(line).group(1) + '),\n'
		print output
		o.write(output)
o.close()
