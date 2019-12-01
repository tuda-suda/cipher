cipher - encrypt-decrypt project from [hyperskill](https://hyperskill.org/projects/46)


usage:java Main [-mode {_enc_|_dec_}]   
                [-key <_int_>]   
                [-data <_String_>]   
                [-in <_filename_>]   
                [-out <_filename_>]  
                [-alg <_algorithm_>]  

CLI options:
Order of arguments is not important. Every argument must have a value.

  **-mode** {_enc_|_dec_}  
     enc - ecnrypt data - default  
     dec - decrypt data  
     
  **-key** <_int_>  
     Sets the key for encryption/decryption. Default is 0.  
     
  **-data** <_String_>   
     Java string to encrypt/decrypt. Default is "" (empty string).  
  
  **-in** <_filename_>  
     Set the name of file to read data from. If both _-data_ and _-in_ arguments given, _-data_ is prioritized, _-in_ is ignored.
     
  **-out** <_filename_>  
   Set the name of file to write to. If not given, result outputs to console.  
     
  **-alg** <_algorithm_>  
     Selects the algorithm of encryption.  
     Algorithms: _shift_, _unicode_.  
     **shift** is a ROT-N encryption, N is given in a _-key_ argument. Shifts only by alphabet characters.  
     **unicode** is similar to shift, but it shifts by all unicode characters.  
     
