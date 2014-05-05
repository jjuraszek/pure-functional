(ns pure-functional.math
  (:gen-class))

(defn max-common-denominator
  "Count maximum number which is common denominator of two of them"
  [a b]
  (def euclids-max-common-denom
  	(fn [ higher lower]
  		(let [tail (mod higher lower)]
  			(if (= 0 tail)
  				lower
  				(recur lower tail)))))
  (euclids-max-common-denom (max a b) (min a b)))
