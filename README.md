bullshit-paper
==============

# Run

You have to have [maven](http://maven.apache.org) and git. Simply clone:

```
git clone https://github.com/neojski/bullshit-paper
```

Then you can use just maven stuff to install dependencies:

```
mvn package
```

It also runs tests. In case you're changing something tests can be fired with
`mvn test`. (use `-Dtest=bullshit_paper.NameOfTest` to run single test)

# Dict
To use the `Dict` class you have to download the dictionary from
[sjp](http://sjp.pl/slownik/odmiany) and then convert it to utf8:

```
iconv -f WINDOWS-1250 -t utf-8 odm.txt > odm
```

Once you're done just throw it into the resources directory for dictionary (`/bullshit-paper/src/main/resources/dict`)

