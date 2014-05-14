(ns pure-functional.rand)

(declare *mod48)

(defn linear-congruential
  "return function which is generating random number vector on provided seed"
  ([] (linear-congruential (. Integer MAX_VALUE)))
  ([upperBound] (linear-congruential (.longValue (System/currentTimeMillis)) upperBound))
  ([prev upperBound] (let [seed (Math/abs (long prev))
                           m (.longValue (Math/pow 2 48))
                           uslessBits (Math/pow 2 16)
                           multiplier 25214903917
                           increment 11
                           new-random-num (mod 
                                            (+ (*mod48 seed multiplier) increment) 
                                            m)]
    (cons (mod (bit-shift-right new-random-num 16) upperBound) (lazy-seq (linear-congruential new-random-num upperBound))))))

(defn *mod48
  [a b]
  (let [p48 (.longValue (Math/pow 2 48))
        p24 (.longValue (Math/pow 2 24))
        a1 (quot a p24)
        a0 (mod a p24)
        b1 (quot b p24)
        b0 (mod b p24)]
    (mod 
      (+ 
        (* 
          (mod 
            (+
              (* a1 b0) 
              (* a0 b1))
            p24)
          p24)
        (* a0 b0)) 
      p48)))