usage() {
  cat <<HERE
Usage:
  build.sh --from-local-dist [--archive <archive>] [--image-name <image>]
  build.sh --from-release --karaf-version <x.x.x> [--image-name <image>]
  build.sh --help
  If the --image-name flag is not used the built image name will be 'karaf'.
HERE
  exit 1
}

while [ $# -ge 1 ]
do
key="$1"
  case $key in
    --from-local-dist)
    FROM_LOCAL="true"
    ;;
    --from-release)
    FROM_RELEASE="true"
    ;;
    --image-name)
    IMAGE_NAME="$2"
    shift
    ;;
    --archive)
    ARCHIVE="$2"
    shift
    ;;
    --karaf-version)
    KARAF_VERSION="$2"
    shift
    ;;
    --help)
    usage
    ;;
    *)
    # unknown option
    ;;
  esac
  shift
done

IMAGE_NAME=${IMAGE_NAME:-karaf}

# TMPDIR must be contained within the working directory so it is part of the
# Docker context. (i.e. it can't be mktemp'd in /tmp)
TMPDIR=_TMP_

cleanup() {
    rm -rf "${TMPDIR}"
}
trap cleanup EXIT

mkdir -p "${TMPDIR}"

if [ -n "${FROM_RELEASE}" ]; then

  [ -n "${KARAF_VERSION}" ] || usage

  KARAF_BASE_URL="$(curl -s https://www.apache.org/dyn/closer.cgi\?preferred\=true)karaf/${KARAF_VERSION}/"
  KARAF_DIST_FILE_NAME="apache-karaf-${KARAF_VERSION}.tar.gz"
  CURL_OUTPUT="${TMPDIR}/${KARAF_DIST_FILE_NAME}"

  echo "Downloading ${KARAF_DIST_FILE_NAME} from ${KARAF_BASE_URL}"
  curl -s ${KARAF_BASE_URL}${KARAF_DIST_FILE_NAME} --output ${CURL_OUTPUT}

  KARAF_DIST="${CURL_OUTPUT}"

elif [ -n "${FROM_LOCAL}" ]; then

  if [ -n "${ARCHIVE}" ]; then
     DIST_DIR=${ARCHIVE}
  else 
     DIST_DIR=../apache-karaf/target/apache-karaf-*.tar.gz
  fi
  KARAF_DIST=${TMPDIR}/apache-karaf.tar.gz
  echo "Using karaf dist: ${DIST_DIR}"
  cp ${DIST_DIR} ${KARAF_DIST}

else

  usage

fi

docker build --build-arg karaf_dist="${KARAF_DIST}" -t "${IMAGE_NAME}" .