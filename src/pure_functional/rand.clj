(ns pure-functional.rand)

(declare *mod32)

(defn linear-congruential
  "return function which is generating random number vector on provided seed"
  ([] (linear-congruential (.intValue (System/currentTimeMillis))))
  ([prev] (let [seed (Math/abs (int prev))
                m (.intValue (Math/pow 2 32))
                multiplier 25214903917
                increment 11
                new-random-num (mod 
                                  (+ 
                                    (*mod32 seed multiplier) 
                                    increment) 
                                  m)]
    (cons new-random-num (lazy-seq (linear-congruential new-random-num))))))

(defn *mod32
  [a b]
  (let [p32 (.intValue (Math/pow 2 32))
        p16 (.intValue (Math/pow 2 16))
        a1 (quot a p16)
        a0 (mod a p16)
        b1 (quot b p16)
        b0 (mod b p16)]
    (mod 
      (+ 
        (* 
          (mod 
            (+
              (* a1 b0) 
              (* a0 b1))
            p16)
          p16)
        (* a0 b0)) 
      p32)))