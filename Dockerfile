### 1. Get Linux
FROM alpine:3.5


### 2. Get Java via the package manager
RUN apk update \
&& apk upgrade \
&& apk add --no-cache bash \
&& apk add --no-cache --virtual=build-dependencies unzip \
&& apk add --no-cache curl \
&& apk add --no-cache vim \
&& apk add --no-cache apache-ant=1.9.7-r1 --repository=http://pkgs.alpinelinux.org/package/v3.5/community \
&& apk add --no-cache make \
&& apk add --no-cache openjdk7

### 3. Get Python, PIP

RUN apk add --no-cache python2
#&& python3 -m ensurepip \
#&& pip3 install --upgrade pip setuptools \
#&& rm -r /usr/lib/python*/ensurepip && \
#if [ ! -e /usr/bin/pip ]; then ln -s pip3 /usr/bin/pip ; fi && \
#if [[ ! -e /usr/bin/python ]]; then ln -sf /usr/bin/python3 /usr/bin/python; fi && \
#rm -r /root/.cache

RUN mkdir /usr/local/DroidSafe

COPY . /usr/local/DroidSafe

WORKDIR /usr/local/DroidSafe

#RUN pip install -r requirements.txt
#ENV PYTHONPATH "${PYTHONPATH}:/usr/local/Mint"

ENV DROIDSAFE_SRC_HOME=/usr/local/DroidSafe
ENV ANDROID_SDK_HOME=/usr/local/DroidSafe/platforms/android-19

ENV JAVA_HOME=/usr/lib/jvm/java-1.7-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"

RUN java -version
RUN javac -version

CMD ["bash"]
