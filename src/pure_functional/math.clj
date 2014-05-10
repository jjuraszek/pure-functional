(ns pure-functional.math)

(defn max-common-denominator
  "Count maximum number which is common denominator of two of them"
  [a b]
  (def euclids-max-common-denom
  	(fn [ higher lower]
      (if (= 0 lower)
        higher
        #(euclids-max-common-denom lower (mod higher lower)))))
  (trampoline euclids-max-common-denom (max a b) (min a b)))

(defn multiply-matrix
  "multiply 2 compatible matrixs"
  [matrix1 matrix2]
  (def value-of-field 
    (fn [row col]
      (reduce +
        (map-indexed 
          (fn [columnIdx leftValue]
            (* leftValue (nth (nth matrix2 columnIdx) col)))
          (nth matrix1 row)))))
  (map
    (fn [rowIdx] 
      (map 
        #(value-of-field rowIdx %)
        (range (count (first matrix2))))) 
    (range (count matrix1))))
