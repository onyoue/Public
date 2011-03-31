
(load-file "/Users/onyoue/MyProject/GLSLEditor/src/glsledit/jcclexer/glsltoken.clj")

;(for [k (keys glsl-words)]
;  (print k (glsl-words k)))

(use '[clojure.contrib.duck-streams :only (writer write-lines)])

(for [k (keys glsl-words)]
  (print k (glsl-words k)))

(write-lines (writer "/Users/onyoue/MyProject/GLSLEditor/src/glsledit/jcclexer/glsltokenid3.txt")
  (map #(str "new GLSLTokenId(\"" (.toUpperCase %) "\", \"" (glsl-words %) "\" , JavaParserConstants." (.toUpperCase %) "),")
    (keys glsl-words)))

(write-lines (writer "/Users/onyoue/MyProject/GLSLEditor/src/glsledit/jcclexer/glsljj.txt")
  (map #(format "| < %s: \"%s\" >" (.toUpperCase %) %)
    (keys glsl-words)))
