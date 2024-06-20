#!/bin/bash

# Create the hooks directory if it doesn't exist
mkdir -p .git/hooks

# Create the pre-commit file and add the script
cat << 'EOF' > .git/hooks/pre-commit
#!/bin/bash

# Run Maven tests
mvn test

# Check if Maven tests failed
if [ $? -ne 0 ]; then
  echo "Maven tests failed. Commit cancelled."
  # Prevent commit
  exit 1
fi

# If tests pass, authorize commit
exit 0
EOF

# Make pre-commit hook executable
chmod +x .git/hooks/pre-commit

echo "Hooks installed."
