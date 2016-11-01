# Project: - Validation of UK Post Code

###Language and platform used to developed the project

    1) Java 6 SE 
    2) Eclipse for Java 6

The prooject UKPostCodeVal has several Java programs having main[] method inside those for testing each task independently. The below is the list of Java classes having the main[] method which can be run to test each task.

    1) AcceptPostCode - to test the part1 task
    2) TestUKPostCode - Junit class to test the part1 task with all the given unit test cases
    3) BulkValidation - to test the part2 task
    4) BulkValidationSorted - to test the part3 task (excluding the performance improvement part)
    5) ImprovedBulkValidation - to test the entire part3 task (including the performance improvement part)

## Part 1 - Postcode validation

Programs used for this part are :-

  1) AcceptPostCode - takes input from user from the console and passes to the validate method of PostCodeVal class          
 2) PostCodeVal - takes the post code and validates against the various regex patterns and returns the validation message

I have added some more patterns and tried to cover almost all the post codes I found in Google inclduing the one given below. All the patterns are described in the PostCodeVal class.

        (GIR\s0AA) |
        (
            # A9 or A99 prefix
            ( ([A-PR-UWYZ][0-9][0-9]?) |
                 # AA99 prefix with some excluded areas
                (([A-PR-UWYZ][A-HK-Y][0-9](?<!(BR|FY|HA|HD|HG|HR|HS|HX|JE|LD|SM|SR|WC|WN|ZE)[0-9])[0-9]) |
                 # AA9 prefix with some excluded areas
                 ([A-PR-UWYZ][A-HK-Y](?<!AB|LL|SO)[0-9]) |
                 # WC1A prefix
                 (WC[0-9][A-Z]) |
                 (
                    # A9A prefix
                   ([A-PR-UWYZ][0-9][A-HJKPSTUW]) |
                    # AA9A prefix
                   ([A-PR-UWYZ][A-HK-Y][0-9][ABEHMNPRVWXY])
                 )
                )
              )
              # 9AA suffix
            \s[0-9][ABD-HJLNP-UW-Z]{2}
            )

The Junit program `TestUKPostCode` is used to validate the post codes against the above given regex pattern for the below mentioned test cases:

###### Another point I would like to mention is that, this kind of specific validation is hadnled for the more common pattern as given above. I have not handled this specific validation for other patterns like the UK Islands etc because I was not sure about the validations.

| Postcode | Expected problem |
|----------|---------|
| $%± ()()| Junk |
| XX XXX | Invalid |
| A1 9A | Incorrect inward code length |
| LS44PL | No space |
| Q1A 9AA| 'Q' in first position |
| V1A 9AA| 'V' in first position|
| X1A 9BB| 'X' in first position|
| LI10 3QP | 'I' in second position |
| LJ10 3QP | 'J' in second position |
| LZ10 3QP | 'Z' in second position |
| A9Q 9AA | 'Q' in third position with 'A9A' structure|
| AA9C 9AA | 'C' in fourth position with 'AA9A' structure	|
|FY10 4PL| Area with only single digit districts|
|SO1 4QQ|  Area with only double digit districts|
| EC1A 1BB | None |
| W1A 0AX | None |
| M1 1AE | None |
| B33 8TH | None |
| CR2 6XH | None |
| DN55 1PT | None |
| GIR 0AA | None |
|SO10 9AA    |None|
|FY9 9AA      |None|
|WC1A 9AA    |None|

Please read (https://en.wikipedia.org/wiki/Postcodes_in_the_United_Kingdom#Validation), does the regular expression validate all UK postcode cases? 

It was very helpful, thank you!!


## Part 2 - Bulk import

I unzipped the data file of around 2 million postcodes ([downloaded from google drive](https://drive.google.com/file/d/0BwxZ38NLOGvoTFE4X19VVGJ5NEk/view?usp=sharing)) named `import_data.csv.gz`  and used as input to my bulk validation process. The structure of the file `import_data.csv.gz` is shown below:

| row_id | postcode |
|--------|----------|
| 1 | AABC 123|
| 2 | AACD 4PQ|
|...|...|

If you need to untar the file, that is acceptable. - yes I did as I mentioned above

Programs used for this part are :-

  1) BulkValidation - takes input file import_data.csv, validates the postcode for eachrow                                     
  2) PostCodeVal - takes the post code and validates against the various regex patterns and returns the validation message

Please update the below two variables in the BulkValidation program to specify the correct folder parth for the i/p and o/p files before running the bulkvalidation process.

  1) final String csvInput = "C:/Users/user/../import_data.csv";             
  2) final String csvOutput = "C:/Users/user/../failed_validation.csv";

A file named, `failed_validation.csv` with the same columns as above is produced with the failed validations.

## Part 3 - Performance engineering

Modify the code in **Part 2** to produce two files:

    succeeded_validation.csv
    failed_validation.csv
    
The postcodes in the two files need to be ordered as per the `row_id`, in ascending numeric order.

Programs used for this part are :-

  1) BulkValidationSorted - takes input file import_data.csv, validates the post code for each row and produces 2 different
     o/p files for successful and failed validations as mentioned above. The files are also sorted in the ascending order 
     of row_id column.
     
  2) PostCodeVal - takes the post code and validates against the various regex patterns and returns the validation message

Like Part3, please update the below three variables in the BulkValidationSorted program to specify the correct folder parth for the i/p and o/p files before running the bulkvalidationSorted process.

  1) final String csvInput = "C:/Users/user/../import_data.csv";
  
  2) final String csvOPass = "C:/Users/user/../succeeded_validation.csv";
  
  3) final String csvOFail = "C:/Users/user/../failed_validation.csv";


Analyse the performance of your solution and make an attempt to optimise the performance of the operation (in terms of overall 'wall' time taken).  Describe how you improved the performance of the code, and how you measured the impact of your changes.

It is acceptable to not use the regular expression (or different regular expression(s)) for this part of the task, but the output in terms of the correctness of the validation needs to match the critieria in **Part 1**. -- This part I haven't changed and kept the same validation process as in Part1. 

However I have tried to implement the threadpool concept to do the parallel processing for the huge file size. But I could not test this fully due to some unavoidable situations before I submit the task. I am sorry, hope you consider this effort.

# Constraints / instructions

Considered as mentioned below.

- Language of your choosing. - Java. Last worked in 2009, I am happy that I can still code in it. 
- Used all standard libraries, no external jar/War used.
- Reused your `README.md`. Thank you for this, it did save some of my time.
- Included notes on my analysis  in the code (e.g. also where I have found the regular expression provided doesn't deal 
  with all UK postcode edge cases).
- Sent an email the task to spine2dev@gmail.com?
- Do not include compiled code or binaries. - No
- Do not include any output files or any postcode test files. - No
