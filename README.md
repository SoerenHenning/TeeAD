# TeeAD - Anomaly Detection with TeeTime
TeeAD is an open source library for time series analysis and anomaly detection with the Pipe-and-Filter framework [TeeTime](http://teetime-framework.github.io/). It provides a TeeTime stage that investigates incoming measurements for anomalies and calculates an anomaly score for them.

Further information can be found in [Sören Henning´s bachelor´s thesis](http://eprints.uni-kiel.de/34141/).

## Install

In order to us TeeAD, you have to add it as a dependency to your favorite build tool. If your are using Maven, copy the following lines to your ``pom.xml``.

```xml
<dependency>
  <groupId>de.soeren-henning</groupId>
  <artifactId>teead</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

Since there is only a snapshot version of TeeAD available so far, you also have to add the Sonatype snapshot repository.

```xml
<repositories>
	<repository>
		<id>sonatype.oss.snapshots</id>
		<url>https://oss.sonatype.org/content/repositories/snapshots/</url>
	</repository>
</repositories>
```
