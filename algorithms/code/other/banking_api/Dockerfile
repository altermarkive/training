FROM python:3.12-alpine

COPY . /tmp/banking

WORKDIR /tmp/banking

RUN pip install --disable-pip-version-check --no-cache-dir . && \
    rm -rf /tmp/banking

WORKDIR /var/db

RUN useradd -m user
USER user

ENTRYPOINT [ "/usr/local/bin/python", "/usr/local/lib/python3.12/site-packages/banking/main.py" ]
